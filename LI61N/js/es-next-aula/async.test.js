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

function timeout (p, ms) {
  return Promise.race([
    p,
    delayError(new Error('timeout'), ms)
  ])
}

test('async 1', async () => {
  const v1 = await delay(1, 100)
  const v2 = v1 + await delay(2, 100)
  expect(v2).toBe(3)
})

async function f1 () {
  const a = await delay(1, 500)
  const b = await delay(1, 500)
  return a + b
}

async function f2 () {
  const a = delay(1, 500)
  const b = delay(1, 500)
  return await a + await b
}

test('sequential', done => {
  timeout(f1(), 750)
    .catch(e => {
      expect(e.message).toBe('timeout')
      done()
    })
})

test('sequential', done => {
  timeout(f2(), 750)
    .then(v => {
      expect(v).toBe(2)
      done()
    })
})

test('async function completes immediatly', done => {
  async function f () {
    const v = await delay(1, 500)
    console.log('after await')
    done()
    return v
  }

  console.log('before calling f')
  f()
  console.log('after calling f')
})
