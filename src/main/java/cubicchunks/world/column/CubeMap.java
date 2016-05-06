/*
 *  This file is part of Cubic Chunks Mod, licensed under the MIT License (MIT).
 *
 *  Copyright (c) 2015 contributors
 *
 *  Permission is hereby granted, free of charge, to any person obtaining a copy
 *  of this software and associated documentation files (the "Software"), to deal
 *  in the Software without restriction, including without limitation the rights
 *  to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 *  copies of the Software, and to permit persons to whom the Software is
 *  furnished to do so, subject to the following conditions:
 *
 *  The above copyright notice and this permission notice shall be included in
 *  all copies or substantial portions of the Software.
 *
 *  THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 *  IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 *  FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 *  AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 *  LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 *  OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 *  THE SOFTWARE.
 */
package cubicchunks.world.column;

import com.google.common.collect.Iterators;
import cubicchunks.world.cube.Cube;

import java.util.*;

class CubeMap implements Iterable<Cube> {
	private final TreeMap<Integer, Cube> cubeMap = new TreeMap<>();
	private final Map<Integer, Cube> map = new HashMap<>();

	Cube get(int cubeY) {
		return map.get(cubeY);
	}

	Cube remove(int cubeY) {
		this.map.remove(cubeY);
		return cubeMap.remove(cubeY);
	}

	boolean put(int cubeY, Cube cube) {
		if(cube == null) {
			throw new NullPointerException();
		}
		if(this.contains(cubeY)) {
			throw new IllegalArgumentException("Cube at " + cubeY + " already exists!");
		}
		this.cubeMap.put(cubeY, cube);
		map.put(cubeY, cube);
		return true;
	}

	Iterable<Cube> cubes(int minCubeY, int maxCubeY) {
		return this.cubeMap.subMap(minCubeY, true, maxCubeY, true).values();
	}

	private boolean contains(int cubeY) {
		return this.map.containsKey(cubeY);
	}

	@Override public Iterator<Cube> iterator() {
		return Iterators.unmodifiableIterator(this.map.values().iterator());
	}

	public Collection<Cube> all() {
		return Collections.unmodifiableCollection(this.map.values());
	}

	public boolean isEmpty() {
		return this.cubeMap.isEmpty();
	}
}
