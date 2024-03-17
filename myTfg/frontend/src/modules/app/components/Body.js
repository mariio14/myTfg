import React from "react";
import { Route, Routes } from "react-router-dom";
import { useSelector } from 'react-redux';
import users, {Login, SignUp, UpdateProfile, ChangePassword, Logout, UserProfile} from '../../users';
import Home from "./Home";
import { Notifications } from "../../notification";
import {Post, PostDetails, UpdatePost} from "../../post";
import MyFeed from "../../post/components/MyFeed";



const Body = () => {


	const loggedIn = useSelector(users.selectors.isLoggedIn);

	return (
		<Routes>
			<Route path="/*" element={<Home />} />
			{!loggedIn && <Route path="/users/login" element={<Login />} />}
			{!loggedIn && <Route path="/users/signUp" element={<SignUp />} />}
			{loggedIn && <Route path="/users/update-profile" element={<UpdateProfile />} />}
			{loggedIn && <Route path="/users/change-password" element={<ChangePassword />} />}
			{loggedIn && <Route path="/users/logout" element={<Logout />} />}
			{loggedIn && <Route path="/notifications/notifications" element={<Notifications />} />}
			{loggedIn && <Route path="/users/createPost" element={<Post />} />}
			{loggedIn && <Route path="/users/updatePost/:id" element={<UpdatePost />} />}
			{loggedIn && <Route path="/users/myFeed" element={<MyFeed />} />}
			{<Route path="/feed/posts/:id" element={<PostDetails />} />}
			{<Route path="/users/:id" element={<UserProfile />} />}
		</Routes>
	);
};

export default Body;
