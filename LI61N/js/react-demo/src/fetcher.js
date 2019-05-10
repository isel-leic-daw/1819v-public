import React from 'react'

const FetchState = {
  loading: 'loading',
  loaded: 'loaded',
  error: 'error',
  reloading: 'reloading'
}

export default class extends React.Component {
  constructor (props) {
    super(props)
    this.state = {
      loadState: FetchState.loading
    }
  }

  render () {
    switch (this.state.loadState) {
      case FetchState.loading: return this.renderLoading()
      case FetchState.loaded: return this.renderLoaded()
      case FetchState.error: return this.renderError()
      case FetchState.reloading: return this.renderReloading()
    }
  }

  renderLoading () {
    return this.props.onLoading()
  }

  renderLoaded () {
    return this.props.onLoaded(this.state.json)
  }

  renderError () {
    return this.props.onError(this.state.error)
  }

  renderReloading () {
    return this.props.onReloading(this.state.json)
  }

  componentDidMount () {
    this.load(this.props.url)
  }

  componentDidUpdate (prevProps) {
    if (this.props.url !== prevProps.url) {
      this.setState({
        loadState: this.state.loadState === FetchState.loaded
          ? FetchState.reloading
          : FetchState.loading
      })
      this.load(this.props.url)
    }
  }
  // TODO cancellation!
  async load (url) {
    try {
      let res = await fetch(url)
      if (res.status === 200) {
        let json = await res.json()
        this.setState({
          loadState: FetchState.loaded,
          json: json
        })
      } else {
        this.setState({
          loadState: FetchState.error,
          error: { message: `non-200 status (${res.status})` }
        })
      }
    } catch (error) {
      this.setState({
        loadState: FetchState.error,
        error: error
      })
    }
  }
}
