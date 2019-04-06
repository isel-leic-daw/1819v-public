import React from 'react'
import ReactDOM from 'react-dom'

// Render a single element with text inside
function example0 () {
  ReactDOM.render(
    <h1>Hello using JSX</h1>,
    document.getElementById('container')
  )
}

function example1 () {
  setInterval(() => {
    ReactDOM.render(
      <div>
        <h1>Current date and time</h1>
        <p>{new Date().toLocaleString()}</p>
      </div>,
      document.getElementById('container'))
  }, 1000)
}

function Show ({ date }) {
  const seconds = date.getSeconds()
  const line = [...Array(seconds).keys()].map(i => React.createElement('span', { key: i }, ` ${i} `))

  return (
    <div>
      <h1>Current data and time</h1>
      <p>{date.toLocaleString()}</p>
      <div>{line}</div>
    </div>
  )
}

function example3 () {
  setInterval(() => {
    ReactDOM.render(
      <div><Show date={new Date()} /></div>,
      document.getElementById('container'))
  }
  , 1000)
}

export { example0, example1, example3 }
