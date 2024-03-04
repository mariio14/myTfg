import React, { useState, useEffect } from "react";
import "./App.css";
import { config } from "../../../config/constants.js";
import { Link } from "react-router-dom";
import {useDispatch, useSelector} from "react-redux";
import {Feed} from "../../post"

const Home = () => {
  return (
    <div className="Posts-container">
      	<h2>Postssssss</h2>
      	<Feed/>
    </div>
  );
};

export default Home;
