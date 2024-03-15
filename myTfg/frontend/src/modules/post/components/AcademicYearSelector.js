import PropTypes from 'prop-types';

const AcademicYearSelector = (selectProps) => {

    const years = Array.from({ length: 24 }, (_, i) => {
        const year = 2023 - i;
        return `${year}/${year + 1}`;
    });


    return (

        <select {...selectProps}>

            <option  value="" disabled>Selecciona un curso</option>

            {years && years.map(year =>
                <option key={year} value={year}>{year}</option>
            )}

        </select>

    );

}

AcademicYearSelector.propTypes = {
    selectProps: PropTypes.object
};

export default AcademicYearSelector;