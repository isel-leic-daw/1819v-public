import React from 'react'
import ReactDOM from 'react-dom'

const Line = (props) => [...Array(props.s).keys()].map(i => <span key={i} > {i} </span>)

class Clock extends React.Component {
  constructor (props) {
    console.log('ctor')
    super(props)
    this.state = { date: new Date() }
  }

  render () {
    console.log('render')
    return (
      <div>
        <h1>Current date and time</h1>
        <p>{this.state.date.toLocaleString()}</p>
        <div>
          <Line s={this.state.date.getSeconds()} />
        </div>
      </div>
    )
  }

  componentDidMount () {
    console.log('CDM')
    this.timer = setInterval(
      () => this.tick(),
      1000
    )
  }

  componentWillUnmount () {
    console.log('CWU')
    clearInterval(this.timer)
  }

  tick () {
    console.log('tick')
    this.setState({ date: new Date() })
  }
}

function example0 () {
  ReactDOM.render(
    <div>
      <h1> Example using state</h1>
      <Clock />
    </div>,
    document.getElementById('container'))
}

class DateProvider extends React.Component {
  constructor (props) {
    console.log('ctor')
    super(props)
    this.state = { date: new Date() }
  }

  render () {
    console.log('render')
    return this.props.children(this.state.date)
  }

  componentDidMount () {
    console.log('CDM')
    this.timer = setInterval(
      () => this.tick(),
      1000
    )
  }

  componentWillUnmount () {
    console.log('CWU')
    clearInterval(this.timer)
  }

  tick () {
    console.log('tick')
    this.setState({ date: new Date() })
  }
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

function example1 () {
  ReactDOM.render(
    <div>
      <h1> Example using function children</h1>
      <DateProvider>
        {(date) => <Show date={date} />}
      </DateProvider>
    </div>,
    document.getElementById('container'))
}

export { example0, example1 }
