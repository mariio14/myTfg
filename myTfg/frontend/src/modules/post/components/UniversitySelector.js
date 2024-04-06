import { useSelector} from 'react-redux';
import PropTypes from 'prop-types';
import * as selectors from '../selectors';

const UniversitySelector = (selectProps) => {

    const universities = useSelector(selectors.getUniversities);

    return (

        <select {...selectProps}>

            <option  value="" disabled>Selecciona una universidad</option>

            {universities && universities.map(university =>
                <option key={university.id} value={university.id}>{university.name}</option>
            )}

        </select>

    );

}

UniversitySelector.propTypes = {
    selectProps: PropTypes.object
};

export default UniversitySelector;