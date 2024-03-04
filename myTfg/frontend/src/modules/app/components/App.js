import React from "react";

import Body from "./Body";
import Header from "./Header";
import './App.css';
import {useDispatch} from "react-redux";
import post from '../../post'

const App = () => {

    const dispatch = useDispatch();

    dispatch(post.actions.findAllUniversities());

  return (
    <div>
      <Header/>
      <Body/>

    </div>
  );
};

export default App;
