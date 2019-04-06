test('rest in parameters', () => {
  function f (a, b, ...c) {
    return [a, b, c]
  }

  expect(f(1, 2, 3, 4)).toEqual([1, 2, [3, 4]])
})

test('rest in lambda parameters', () => {
  const f = (a, b, ...c) => {
    return [a, b, c]
  }

  expect(f(1, 2, 3, 4)).toEqual([1, 2, [3, 4]])
})

test('spread in parameters', () => {
  function f (a, b, c, d, e) {
    return [a, b, c, d, e]
  }

  const arr = [1, 2, 3, 4, 5]
  expect(f(arr)).toEqual([[1, 2, 3, 4, 5],
    undefined, undefined, undefined, undefined])

  expect(f(...arr)).toEqual([1, 2, 3, 4, 5])
})

test('spread and rest in parameters', () => {
  function f (a, b, ...c) {
    return [a, b, c]
  }

  const arr = [1, 2, 3, 4, 5]
  const res = f(...arr)
  expect(res).toEqual([1, 2, [3, 4, 5]])
})

test('spread in array construction', () => {
  const arr = [3, 4, 5]
  const arr2 = [1, 2, arr]

  expect(arr2).toEqual([1, 2, [3, 4, 5]])

  const arr3 = [1, 2, ...arr]
  expect(arr3).toEqual([1, 2, 3, 4, 5])
})

test('rest in array destructuring', () => {
  const [a, b, c] = [1, 2, 3, 4, 5, 6]

  expect(a).toBe(1)
  expect(b).toBe(2)
  expect(c).toBe(3)

  const [x, y, ...z] = [1, 2, 3, 4, 5, 6]

  expect(x).toBe(1)
  expect(y).toBe(2)
  expect(z).toEqual([3, 4, 5, 6])
})

test('spread in array construction', () => {
  const arr = [3, 4, 5]
  const arr2 = [1, 2, arr]

  expect(arr2).toEqual([1, 2, [3, 4, 5]])

  const arr3 = [1, 2, ...arr]
  expect(arr3).toEqual([1, 2, 3, 4, 5])
})

test('rest in object deconstruction', () => {
  const o = { a: 1, b: 2, c: 3 }
  const { a: x, ...y } = o

  expect(x).toBe(1)
  expect(y).toEqual({ b: 2, c: 3 })
})

test('spread in object construction', () => {
  const o1 = { a: 1, b: 2 }
  const o2 = { ...o1, c: 3 }
  expect(o2).toEqual({ a: 1, b: 2, c: 3 })
})
