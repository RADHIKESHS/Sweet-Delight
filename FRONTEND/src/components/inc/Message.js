import React from 'react'

const errorStyle = {
  border: '1px solid #a24b4b',
  background: '#ffe1e1',
  padding: '5px 10px',
  color: '#a24b4b'
}

const successStyle = {
  border: '1px solid rgb(75 162 90)',
  background: 'rgb(225 255 238)',
  padding: '5px 10px',
  color: 'rgb(75 162 90)'
}
export default function Message({error, success}) {
  if(error == null && success == null) return null
  return (
    <div style={error ? errorStyle:successStyle}>
        {error || success}
    </div>
  )
}
