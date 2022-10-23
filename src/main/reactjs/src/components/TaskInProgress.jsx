import React from 'react';


const TaskInProgress = (props) => {
    return (
        <div className="task">

            <span>Рейс {props.name}</span>
            <span>Статус {props.busStatus}</span>
        </div>
    );
};
TaskInProgress.defaultProps = {name: "555", busStatus:"Отправляется"};
export default TaskInProgress;