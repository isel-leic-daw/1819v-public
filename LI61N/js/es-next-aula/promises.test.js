/* test('simple promise', done => {
  const p = new Promise((resolve, reject) => {
    setTimeout(() => resolve(1), 4 * 1000)
  })

  const p2 = p.then(v => v + 1)
  const p3 = p2.then(v => 2 * v)
  p3.then(v => {
    console.log(v)
    done()
  })
})

test('simple promise 2', done => {
  const p = new Promise((resolve, reject) => {
    setTimeout(() => resolve(1), 4 * 1000)
  })

  p.then(v => v + 1)
    .then(v => 2 * v)
    .then(v => {
      console.log(v)
      done()
    })
})
*/

test('simple promise 2', done => {
  const p = new Promise((resolve, reject) => {
    setTimeout(() => resolve(1), 1 * 1000)
  })

  p.then(v => { throw new Error() })
    .then(v => 2 * v)
    .then(v => v + 1)
    .catch(e => 1)
    .then(v => v + 1)
    .then(v => {
      expect(v).toBe(2)
      done()
    })
})

function delay (value, ms) {
  return new Promise((resolve, reject) => {
    setTimeout(() => resolve(value), ms)
  })
}

function delayError (error, ms) {
  return new Promise((resolve, reject) => {
    setTimeout(() => reject(error), ms)
  })
}

test('race', done => {
  const p1 = delay(1, 500)
  const p2 = delay(2, 250)
  const p3 = Promise.race([p1, p2])
  p3.then(v => {
    expect(v).toBe(2)
    done()
  })
})

test('race with errors', done => {
  const p1 = delay(1, 500)
  const p2 = delayError(new Error('error-msg'), 250)
  const p3 = Promise.race([p1, p2])
  p3.catch(e => {
    expect(e.message).toBe('error-msg')
    done()
  })
})

test('race with errors', done => {
  const p1 = delay(1, 250)
  const p2 = delayError(new Error('error-msg'), 500)
  const p3 = Promise.race([p1, p2])
  p3.then(v => {
    expect(v).toBe(1)
    done()
  })
})

test('all', done => {
  const p1 = delay(1, 500)
  const p2 = delay(2, 250)
  const p3 = Promise.all([p1, p2])
  p3.then(v => {
    expect(v).toEqual([1, 2])
    done()
  })
})

test('all with errors', done => {
  const p1 = delay(1, 500)
  const p2 = delayError(new Error('error-msg'), 250)
  const p3 = Promise.all([p1, p2])
  p3.catch(error => {
    expect(error.message).toEqual('error-msg')
    done()
  })
})

function timeout (p, ms) {
  return Promise.race([
    p,
    delayError(new Error('timeout'), ms)
  ])
}

test('timeout 1', done => {
  const p = delay(1, 500)
  const p2 = timeout(p, 1000)
  p2.then(v => {
    expect(v).toBe(1)
    done()
  })
})

test('timeout 2', done => {
  const p = delay(1, 500)
  const p2 = timeout(p, 250)
  p2.catch(e => {
    expect(e.message).toBe('timeout')
    done()
  })
})
