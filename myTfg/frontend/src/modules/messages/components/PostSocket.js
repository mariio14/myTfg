import React, { useEffect, useState, useRef } from 'react';
import { useDispatch, useSelector } from 'react-redux';
import { Client } from '@stomp/stompjs';
import * as selectors from "../../users/selectors";
import * as actions from "../actions";

const PostSocket = (params) => {
	const dispatch = useDispatch();
	const stompClientRef = useRef(null);
	const websocketUrl = "ws://localhost:8080/mytfg/wss";
	const id = useSelector(selectors.getUserId);

	useEffect(() => {
		stompClientRef.current = new Client({
			brokerURL: websocketUrl
		});

		stompClientRef.current.onConnect = () => {
			stompClientRef.current.subscribe(`/topic/chat.${id}.${params.id}`, () => {
				handleRefresh();
			});
		};

		stompClientRef.current.activate();

		return () => {
			if (stompClientRef.current) {
				stompClientRef.current.deactivate();
				stompClientRef.current = null;
			}
		};
	}, []);

	const handleRefresh = () => {
		const userId = Number(params.id);
		if(!Number.isNaN(userId)){
			dispatch(actions.getMessages(userId));
		}
	};

	const messageReceive = () => {
	};

	return (
		<div></div>
	);
};

export default PostSocket;
