import React from 'react'
import { BrowserRouter as Router, Route, Link, Switch } from "react-router-dom"

const Some = () => <h2>Some</h2>
const Another = () => <h2>Another</h2>
const Home = () => <h1>Home</h1>
const Path = () => <h2>Path</h2>
const Projects = ({ match }) => (
  <>
    <h1>Projects</h1>
    <p>Details for project {match.params.pid}</p>
  </>
)

export default (props) => (
  <Router>
    <ul>
      <li><Link to='/path/some'>/path/some</Link></li>
      <li><Link to='/path/another'>/path/another</Link></li>
      <li><Link to='/'>/</Link></li>
      <li><Link to='/foo/bar'>/foo/bar</Link></li>
      <li><Link to='/projects/1'>/projects/1</Link></li>
      <li><Link to='/projects/2'>/projects/2</Link></li>
    </ul>
    <Switch>
      <Route path='/path/some' component={Some} />
      <Route path='/path/another' component={Another} />
      <Route path='/path' component={Path} />
      <Route path='/projects/:pid' exact component={Projects} />
      <Route path='/' exact component={Home} />
      <Route path='/' render={({ location }) => (
        <h1>{location.pathname} Not Found</h1>
      )} />
    </Switch>
  </Router>
)
