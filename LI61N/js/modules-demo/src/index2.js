import { increment } from './helper'
import leftPad from 'left-pad'

const s = 'Hello' + leftPad('World', 20, '-')
document.write(s)
document.write(increment())
document.write(increment())
document.write(increment())
