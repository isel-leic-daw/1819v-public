import React from 'react'
import Fetcher from './fetcher'

export default class extends React.Component {
  constructor (props) {
    super(props)
    this.state = {
      editUrl: 'https://httpbin.org/get',
      url: 'https://httpbin.org/get'
    }
    this.onChangeHandler = this.onChangeHandler.bind(this)
    this.onClickHandler = this.onClickHandler.bind(this)
  }

  onChangeHandler (ev) {
    console.log('onChangeHandler')
    this.setState({ editUrl: ev.target.value })
  }

  onClickHandler (ev) {
    ev.preventDefault()
    this.setState(state => ({ url: state.editUrl }))
  }

  render () {
    return (
      <form>
        <input
          type='text' value={this.state.editUrl}
          onChange={this.onChangeHandler} />
        <input type='submit' onClick={this.onClickHandler} />
        <p>{this.state.url}</p>
        <Fetcher url={this.state.url}
          onLoading={() => <p>on loading...</p>}
          onLoaded={(json) => <pre>{JSON.stringify(json)}</pre>}
          onError={(error) => <p>{JSON.stringify(error)} occured</p>}
          onReloading={(json) => (
            <>
              <p>Reloading...</p>
              <pre>{JSON.stringify(json)}</pre>
            </>
          )}
        />
      </form>
    )
  }
}
