import React from 'react'
import RepoSelect from './repo-select'
import IssueList from './issue-list'
import RouterIntro from './router-intro'
import FetcherExample from './fetcher-example'

const repos = {
  'daw': 'https://api.github.com/repos/isel-leic-daw/1819v-public/issues',
  'react': 'https://api.github.com/repos/facebook/react/issues'
}

export default function (props) {
  return (
    <>
      <FetcherExample />
    </>
  )
}
