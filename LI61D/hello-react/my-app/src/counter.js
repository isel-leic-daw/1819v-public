import React from 'react'

export function initCounter(initialValue) {
    return {
        value: initialValue,
        increment: function () {
            this.value += 1
            return this
        }
    }
}

export class Counter extends React.Component {

    constructor(props) {
        super(props)
        this.state = this.props.model
    }

    increment() {
        this.setState(() => this.state.increment())
    }

    render() {
        return (
            <div> 
                <span> {this.state.value} </span>
                <button onClick={ () => { this.increment() } }>Inc</button>
            </div>
        )
    }
}