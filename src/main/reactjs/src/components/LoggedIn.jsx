import React, {useState} from 'react';

const LoggedIn = () => {
    // the height of the box
    const [hour, setHours] = useState(12);

    // This function is called when the second range slider changes
    const changeHours = (event: React.ChangeEvent<HTMLInputElement>) => {
        setHours(parseInt(event.target.value));
    };

    return (
        <div className="loggedStyle">
            <div >
                <h2>Профиль</h2>
                <img className="circle" src="https://get.pxhere.com/photo/person-people-portrait-facial-expression-hairstyle-smile-emotion-portrait-photography-134689.jpg"></img>

                <div className='container'>
                    <h4>Показывать записи за ближайшие: {hour}ч</h4>
                    <input
                        type='range'
                        onChange={changeHours}
                        min={1}
                        max={24}
                        step={1}
                        value={hour}
                        className='custom-slider'
                    ></input>
                </div>

            </div>
        </div>
    );
};

export default LoggedIn;