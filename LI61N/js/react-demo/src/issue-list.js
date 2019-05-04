import React from 'react'
import fetch from 'isomorphic-fetch'
import PaginatorPanel from './paginator-panel'

const FetchStates = {
  loading: 'loading',
  loaded: 'loaded',
  error: 'error'
}

function delay (ms) {
  return new Promise((resolve, reject) => {
    setTimeout(() => resolve(), ms)
  })
}

class IssueList extends React.Component {
  // props = {url}
  constructor (props) {
    super(props)
    this.state = {
      fetchState: FetchStates.loading
    }
    this.handlePagination = this.handlePagination.bind(this)
  }

  componentDidMount () {
    console.log('issue-list:CDM')
    this.mounted = true
    this.load(this.props.url)
  }

  componentWillUnmount () {
    this.mounted = false
  }

  async load (url) {
    this.safeSetState({
      fetchState: FetchStates.loading
    })

    try {
      await delay(3000)
      let resp = await fetch(url)
      if (resp.status === 200) {
        let json = await resp.json()
        this.safeSetState({
          fetchState: FetchStates.loaded,
          json: json,
          response: resp
        })
      } else {
        this.safeSetState({
          fetchState: FetchStates.error,
          error: 'unexpected status code'
        })
      }
    } catch (error) {
      this.safeSetState({
        fetchState: FetchStates.error,
        error: error
      })
    }
  }

  safeSetState (newState) {
    if (this.mounted) {
      this.setState(newState)
    }
  }

  handlePagination (url) {
    this.load(url)
  }

  render () {
    return (
      <div>
        <PaginatorPanel response={this.state.response} onClick={this.handlePagination} />
        <div>
          {this.renderContent()}
        </div>
      </div>
    )
  }

  renderContent () {
    switch (this.state.fetchState) {
      case FetchStates.loading:
        return this.renderLoading()
      case FetchStates.loaded:
        return this.renderLoaded()
      case FetchStates.error:
        return this.renderError()
    }
  }

  renderLoading () {
    return <div>... loading ...</div>
  }

  renderLoaded () {
    const issues = this.state.json
    const style = {
      marginTop: '10px'
    }
    return (
      <div>
        {issues.map(issue => <div key={issue.id} style={style}>{issue.state}-{issue.title}</div>)}
      </div>
    )
  }

  renderError () {
    return <div> ERROR </div>
  }
}

export default function ({ url }) {
  return <IssueList url={url} key={url} />
}
