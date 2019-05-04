import React from 'react'
import RepoSelect from './repo-select'
import IssueList from './issue-list'

const repos = {
  'daw': 'https://api.github.com/repos/isel-leic-daw/1819v-public/issues',
  'react': 'https://api.github.com/repos/facebook/react/issues'
}

export default function (props) {
  return (
    <RepoSelect
      repos={repos}
      render={({ name, url }) => (
        <>
          <h1>{name}</h1>
          <IssueList url={url} />
        </>
      )}
    />
  )
}
