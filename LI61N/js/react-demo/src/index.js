import ReactDOM from 'react-dom'
import React from 'react'
import App from './app'
import { userManager } from './oidc'

userManager.signinPopupCallback()

ReactDOM.render(
  <App />,
  document.getElementById('container')
)
