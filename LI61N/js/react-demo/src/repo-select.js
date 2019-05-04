import React from 'react'

// {repos:[{name, url}], render}
export default class extends React.Component {
  constructor (props) {
    super()
    this.state = {
      repoKey: Object.keys(props.repos)[0]
    }
    this.handleChange = this.handleChange.bind(this)
  }

  handleChange (ev) {
    this.setState({
      repoKey: ev.target.value
    })
    console.log(ev.target.value)
  }

  render () {
    let url = this.props.repos[this.state.repoKey]
    return (
      <div>
        <select value={this.state.repoKey} onChange={this.handleChange}>
          {Object.keys(this.props.repos).map(key =>
            <option value={key}
              key={key}>{key}</option>)}
        </select>
        {this.props.render({
          url: url,
          name: this.state.repoKey })}
      </div>
    )
  }
}
