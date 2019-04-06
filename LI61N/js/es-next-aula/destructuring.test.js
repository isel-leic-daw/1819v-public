test('basic destructuring', () => {
  function getStudent () {
    return { name: 'Alice', number: 12345 }
  }

  const { name, number } = getStudent()

  expect(name).toBe('Alice')
  expect(number).toBe(12345)

  const obj = getStudent()
  const name1 = obj.name
  const number1 = obj.number

  expect(name1).toBe('Alice')
  expect(number1).toBe(12345)
})

test('basic destructuring with renaming', () => {
  function getStudent () {
    return { name: 'Alice', number: 12345 }
  }

  const { name: newName, number: newNumber } = getStudent()

  expect(newName).toBe('Alice')
  expect(newNumber).toBe(12345)
})

test('parameter destructuring', () => {
  function getStudent () {
    return { name: 'Alice', number: 12345 }
  }

  function toString ({ name, number }) {
    return `name: ${name}, number: ${number}`
  }

  function toString2 (student) {
    return `name: ${student.name}, number: ${student.number}`
  }

  const alice = getStudent()
  const str = toString(alice)
  expect(str).toBe('name: Alice, number: 12345')

  const str2 = toString2(alice)
  expect(str2).toBe('name: Alice, number: 12345')
})
