import React from 'react'

import { UserManager } from 'oidc-client'

var settings = {
  authority: 'http://localhost:8080/openid-connect-server-webapp',
  client_id: 'oidc-client1',
  redirect_uri: 'http://localhost:9000/index.html',
  popup_redirect_uri: 'http://localhost:9000/index.html',
  response_type: 'code',
  scope: 'openid email read-project',
  loadUserInfo: true
}

export const userManager = new UserManager(settings)

export class OidcDemo extends React.Component {
  constructor (props) {
    super(props)
    this.state = {
      authenticated: false
    }
    this.handleAuthentication = this.handleAuthentication.bind(this)
  }

  render () {
    return !this.state.authenticated
      ? (
        <div>
          <button onClick={this.handleAuthentication} >Get User</button>
        </div>
      ) : (
        <>
          <div>{JSON.stringify(this.state.user)}</div>
        </>
      )
  }

  async handleAuthentication () {
    let user = await userManager.getUser()
    if (!user) {
      try {
        user = await userManager.signinPopup()
      } catch (error) {
        console.log(error)
        this.setState({
          error: error
        })
        return
      }
    }
    this.setState({
      authenticated: true,
      user: user
    })
  }
}
