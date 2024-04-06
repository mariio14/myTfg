import * as actions from './actions';
import * as actionTypes from './actionTypes';
import reducer from './reducer';
import * as selectors from './selectors';

export {default as Post} from "./components/Post.js";
export {default as Feed} from "./components/Feed";
export {default as PostDetails} from "./components/PostDetails";
export {default as FilterAndSearch} from "./components/FilterAndSearch";
export {default as UpdatePost} from "./components/UpdatePost";
export {default as UniversitySelector} from "./components/UniversitySelector";
export {default as AcademicYearSelector} from "./components/AcademicYearSelector"
export {default as SubjectSelector} from "./components/SubjectSelector"
export {default as EtiquetaFeed} from "./components/EtiquetaFeed"

// eslint-disable-next-line
export default {actions, actionTypes, reducer, selectors};