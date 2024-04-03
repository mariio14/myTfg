import { init } from "./appFetch";
import * as userService from "./userService";
import * as ratingService from "./ratingService";
import * as commentService from "./commentService";
import * as notificationService from "./notificationService";
import * as postService from "./postService";
import * as messageService from "./messageService";

export { default as NetworkError } from "./NetworkError";


const backend = {
  init,
  userService,
  ratingService,
  commentService,
  notificationService,
  postService,
  messageService
};

export default backend;
