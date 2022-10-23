import React from 'react';
import Task from "./Task";
import IconButton from "@material-ui/core/IconButton";
import MoreVertIcon from "@material-ui/icons/MoreVert";
import Menu from "@material-ui/core/Menu";
import MenuItem from "@material-ui/core/MenuItem";
import LongMenu from "./Options";

const Queue = () => {
    return (
        <div>
            <Task fromPlace="gate1" toPlace="gate4" timeDate="11:00" busNum="561"/>
            <Task />
            <Task />
            <Task />
            <Task />
            <Task />
            <Task />
            <Task />
            <Task />
            <Task />
            <Task />
            <Task />
            <Task />
            <Task />
            <Task />

        </div>
    );
};

export default Queue;