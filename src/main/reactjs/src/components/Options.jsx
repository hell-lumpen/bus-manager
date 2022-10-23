import React from 'react'
import { Dropdown } from 'semantic-ui-react'

const options = [
    { key: 'new', text: 'Исполнитель', value: 'new' },
    { key: 'save', text: 'Время', value: 'save' },
]

const DropdownExampleUpward = () => (
    <Dropdown upward floating options={options} text='Изменить' />
)

export default DropdownExampleUpward