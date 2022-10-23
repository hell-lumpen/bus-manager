import React, {useState} from "react";
import Task from "./components/Task";
import './styles/App.css'
import Queue from "./components/Queue";
import TaskInProgress from "./components/TaskInProgress";
import Progress from "./components/Progress";
import LoggedIn from "./components/LoggedIn";
import MyModal from "./components/UI/MyModal/MyModal";
import CompletedQueue from "./components/CompletedQueue";


function App() {
    const [modal, setModal] = useState(false);
    /*const [tasks, setTasks] = useState([{}]);*/

    return (
        <div className="App">
            <div className="gridContainer">
                <div className="gridItem queueDiv" id="item1">
                    <div>
                        <h3>Очередь</h3>
                    </div>
                    {/*{tasks.length ! == 0
                    ? <Queue />
                    :<div>Записи не найдены</div>
                    }найдены ли посты*/}

                    <Queue />
                </div>
                <div className="gridItem inProcess" id="item2">
                    <h3>Выполняются</h3>
                    <Progress />

                </div>
                <div className="gridItem" id="item3">
                    <button className="buttonStyle" onClick={() => setModal(true)}>Завершенные задачи</button>
                    <MyModal className="myModalContent" visible={modal} setVisible={setModal}>
                        <CompletedQueue />
                    </MyModal>
                    <LoggedIn />
                </div>
            </div>
        </div>
    );
}

export default App;