import React from 'react'
import ReactDOM from 'react-dom'

// Based on the React documents at https://reactjs.org/docs/hello-world.html

// Render a single element with text inside
function example0 () {
  ReactDOM.render(
    React.createElement('h1', {}, 'Hello World'),
    document.getElementById('container')
  )
}

// Render a list

const students = [
  { name: 'Alice', number: 123000 },
  { name: 'Bob', number: 456 },
  { name: 'Carol', number: 789 }
]

function Student ({ student }) {
  return (
    <ul>
      <li>Name: {student.name} </li>
      <li>Number: {student.number} </li>
    </ul>
  )
}

function Students ({ studentList }) {
  return (
    <div>
      {studentList.map(student =>
        <Student
          student={student}
          key={student.number}>foo
        </Student>)}
    </div>
  )
}

function example4 () {
  ReactDOM.render(
    <Students studentList={students} />,
    document.getElementById('container')
  )
}

function example1 () {
  setInterval(() => {
    ReactDOM.render(
      React.createElement('div', {},
        React.createElement('h1', {}, 'Current date and time'),
        React.createElement('p', {}, `${new Date().toLocaleString()}`)),
      document.getElementById('container'))
  }, 1000)
}

// Slightly more complex tree
function example2 () {
  setInterval(() => {
    const date = new Date()
    const seconds = date.getSeconds()
    const line = [...Array(seconds).keys()].map(i => React.createElement('span', { key: i }, ` ${i} `))
    ReactDOM.render(
      React.createElement('div', {},
        React.createElement('h1', {}, 'Current date and time'),
        React.createElement('p', {}, `${date.toLocaleString()}`),
        React.createElement('div', {}, line)),
      document.getElementById('container'))
  }, 1000)
}

function Show ({ date }) {
  const seconds = date.getSeconds()
  const line = [...Array(seconds).keys()].map(i => React.createElement('span', { key: i }, ` ${i} `))

  return React.createElement('div', {},
    React.createElement('h1', {}, 'Current date and time'),
    React.createElement('p', {}, `${date.toLocaleString()}`),
    React.createElement('div', {}, line))
}

function example3 () {
  setInterval(() => {
    const date = new Date()
    ReactDOM.render(
      React.createElement('div', {},
        React.createElement(Show, { date: date })),
      document.getElementById('container'))
  }, 1000)
}

export { example0, example1, example2, example3, example4 }
