function delay (value, ms) {
  return new Promise((resolve, reject) => {
    setTimeout(() => resolve(value), ms)
  })
}

const F = class {
  constructor (name, number) {
    this.name = name
    this.number = number
    this.show = this.show.bind(this)
  }

  toString () {
    return `${this.name}, ${this.number}`
  }

  show () {
    console.log('show is here')
    console.log(`${this.name}, ${this.number}`)
  }

  showDelayed () {
    // setTimeout(() => this.show(), 100)
    setTimeout(this.show, 100)
  }
}

const Student = F

test('this issue', async () => {
  const alice = new Student('Alice', 12345)
  alice.showDelayed()
  await delay(1, 500)
})

test('first', () => {
  const alice = new Student('Alice', 12345)
  expect(alice.toString()).toBe('Alice, 12345')
})

test('first', () => {
  const alice = new F('Alice', 12345)
  expect(alice.toString()).toBe('Alice, 12345')
})

test('class is a function', () => {
  expect(typeof Student).toBe('function')
  expect(Student.prototype.toString).toBeDefined()
})

test('result is an object with only the defined fields ', () => {
  const student = new Student('Alice', 12345)
  expect(Object.getOwnPropertyNames(student).length).toBe(3)

  // notice that `show` is already included. Do you know why?
  expect(Object.getOwnPropertyNames(student)).toEqual(['name', 'number', 'show'])
})

test('Since class definitions are expression, some unusual things are possible', () => {
  const equatable = aClass => class extends aClass {
    equals (other) {
      if (this.constructor !== other.constructor) return false
      for (let name of Object.getOwnPropertyNames(this)) {
        if (this[name] !== other[name]) return false
        return true
      }
    }
  }

  // yes, this is perfectly valid Javascript
  // notice that the function argument is a class
  const SomeClass = equatable(class {
    constructor (a, b) {
      this.a = a
      this.b = b
    }
  })

  const o1 = new SomeClass(1, 2)
  const o2 = new SomeClass(1, 2)
  const o3 = new SomeClass(2, 3)
  expect(o1.equals(o2)).toBeTruthy()
  expect(o2.equals(o3)).toBeFalsy()
  expect(o3.equals(42)).toBeFalsy()
})
