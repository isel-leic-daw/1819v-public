import React from 'react'
import ReactDOM from 'react-dom'

import { initCounter, Counter } from './counter'

// import { renderPage } from './clock'
// setInterval(renderPage, 1000)

const counter = initCounter(5)
counter.increment()
counter.increment()

ReactDOM.render(
    <Counter model = { counter } />,
    document.getElementById('root')
)