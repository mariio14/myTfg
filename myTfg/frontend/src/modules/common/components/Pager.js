import PropTypes from 'prop-types';

const Pager = ({ back, next }) => (
	<div className="text-center" data-testid="feed-pager">
		<div style={{ display: "flex", padding: "2rem 30%", justifyContent:"space-between" }}>
			<button className={`btn btn-dark page-item ${back.enabled ? "" : "disabled"}`} data-testid="feed-pager-button-back"
				onClick={back.onClick}>
				Anterior
			</button>

			<button className={`btn btn-dark page-item ${next.enabled ? "" : "disabled"}`} data-testid="feed-pager-button-next"
				onClick={next.onClick}>
				Siguiente
			</button>
		</div>
	</div>

);

Pager.propTypes = {
	back: PropTypes.object.isRequired,
	next: PropTypes.object.isRequired
};

export default Pager;