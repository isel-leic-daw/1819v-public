import React from 'react'
import ReactDOM from 'react-dom'

let model = {
  items: [
    { name: 'item 0', description: 'item0' },
    { name: 'item 1', description: 'item1' },
    { name: 'item 2', description: 'item2' }
  ]
}

// Render function: props -> Element Tree
// "Component" that knows how to show items
function Items ({ model }) {
  return React.createElement('ul', {},
    model.items.map(item => React.createElement('li', { key: item.name },
      `${item.name}, ${item.description}`)
    )
  )
}

function Page ({ model }) {
  return React.createElement('div', {},
    React.createElement(Items, { model }),
    React.createElement(Items, { model })
  )
}
setInterval(() => {
  if (model.items.length === 10) {
    model.items = []
  } else {
    const l = model.items.length
    model.items.push({ name: `item ${l}`, description: `item${l}` })
  }
  ReactDOM.render(
    React.createElement(Page, { model }),
    document.getElementById('container')
  )
}, 1000)
