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
  const line = [...Array(seconds).keys()]
    .map(i => <span key={i}>{` (${i})`}</span>)

  return (
    <div>
      <h1>Current data and time</h1>
      <p>{date.toLocaleString()}</p>
      <div>{line}</div>
    </div>
  )
}

class ShowHide extends React.Component {
  constructor (props) {
    super(props)
    console.log('ShowHide.ctor')
    this.state = {
      show: true,
      buttonValue: 'hide'
    }
  }

  toggle () {
    console.log('Show2.toggle')
    this.setState(({ show }) => show
      ? { show: false, buttonValue: 'show' }
      : { show: true, buttonValue: 'hide' }
    )
  }

  render () {
    console.log('ShowHide.render')
    return (
      <div>
        <button onClick={() => this.toggle()}>[{this.state.buttonValue}]</button>
        {this.state.show ? (
          <div>
            {this.props.children}
          </div>
        ) : <div />}
      </div>
    )
  }
}

class Show2 extends React.Component {
  constructor (props) {
    super(props)
    console.log('Show2.ctor')
    this.state = {
      show: true,
      buttonValue: 'hide'
    }
  }

  render () {
    console.log('Show2.render')
    const { date } = this.props
    const seconds = date.getSeconds()
    const line = [...Array(seconds).keys()]
      .map(i => <span key={i}>{` (${i})`}</span>)

    return (
      <div>
        <button onClick={() => this.toggle()}>{this.state.buttonValue}</button>
        {this.state.show ? (
          <div>
            <h1>Current data and time</h1>
            <p>{date.toLocaleString()}</p>
            <div>{line}</div>
          </div>
        ) : <div />}
      </div>
    )
  }

  toggle () {
    console.log('Show2.toggle')
    this.setState(({ show }) => show
      ? { show: false, buttonValue: 'show' }
      : { show: true, buttonValue: 'hide' }
    )
  }
}

class Clock extends React.Component {
  constructor (props) {
    super(props)
    console.log('Clock.ctor')
    this.state = {
      instant: new Date()
    }
  }

  render () {
    console.log('Clock.render')
    return (
      <ShowHide>
        <Show date={this.state.instant} />
      </ShowHide>
    )
    // return <Show2 date={this.state.instant} key={Math.random()} />
    // return React.createElement(Show2, {date: ...})
  }

  componentDidMount () {
    console.log('Clock.CDM')
    this.intervalId = setInterval(() => this.tick(), 1000)
  }

  componentWillUnmount () {
    clearInterval(this.intervalId)
  }

  tick () {
    console.log('tick')
    this.setState({ instant: new Date() })
  }
}

function example3 () {
  setInterval(() => {
    ReactDOM.render(
      <div><Show date={new Date()} /></div>,
      document.getElementById('container'))
  }
  , 1000)
}

class TextBox extends React.Component {
  constructor (props) {
    super(props)
    this.state = { value: '' }
  }

  render () {
    console.log('TextBox.render')
    return (
      <input type='text' value={this.state.value}
        onChange={ev => this.onChangeHandler(ev.target.value)} />
    )
  }

  onChangeHandler (value) {
    console.log(value)
    this.setState({ value: value.substring(0, 5) })
  }
}

function example4 () {
  ReactDOM.render(
    <div>
      <h1>Example 4</h1>
      <TextBox />
    </div>,
    document.getElementById('container')
  )
}

export { example0, example1, example3, example4 }
