import React from 'react';
import DropdownExampleUpward from "./Options";

const Task = (props) => {
    return (
        <div>
            <div className="task">
                <div>

                    <span>Из {props.fromPlace}</span>

                    <span> В {props.toPlace}</span>

                    <span> Время {props.timeDate}</span>

                    <span> Автобус {props.busNum}</span>

                    <DropdownExampleUpward />

                </div>
            </div>
        </div>

    );
};
/*Task.defaultProps = {fromPlace: "", description: ""};*/
export default Task;