import React from 'react';
import ReactDOM from 'react-dom';

function Tautology({content, text}) {
    return (
        <div>{content} <em>{text}</em></div>
    )
}

class Clock extends React.Component {
    render() {
        return <div>{new Date().toLocaleString()}</div>
    }
}

function renderPage() {
    ReactDOM.render(
        <div>
           <Tautology content="SLB, O MAIOR obviamente!!!" text="WOW" />
           <Clock />
        </div>,
        document.getElementById('root')
    )        
}

setInterval(renderPage, 1000)