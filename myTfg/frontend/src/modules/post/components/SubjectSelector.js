import { useSelector} from 'react-redux';
import PropTypes from 'prop-types';
import * as selectors from '../selectors';

const SubjectSelector = (selectProps) => {

    const subjects = useSelector(selectors.getSubjects);

    return (

        <select {...selectProps}>

            <option  value="" disabled>Selecciona una asignatura</option>

            {subjects && subjects.map(subject =>
                <option key={subject.id} value={subject.id}>{subject.name}</option>
            )}

        </select>

    );

}

SubjectSelector.propTypes = {
    selectProps: PropTypes.object
};

export default SubjectSelector;