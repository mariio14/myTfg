import React, { useEffect, useState } from "react";
import { useDispatch } from 'react-redux';
import {AcademicYearSelector, UniversitySelector, Feed} from "../../post";
import * as actions from '../actions';
import SubjectSelector from "./SubjectSelector";


const FilterAndSearch = () => {
	const dispatch = useDispatch();
	const [order, setOrder] = useState("");
	const [minYear, setMinYear] = useState('');
	const [maxYear, setMaxYear] = useState('');
	const [universityId, setUniversityId] = useState('');
	const [subjectId, setSubjectId] = useState('');
	const [search, setSearch] = useState('');
	const [isCardExpanded, setIsCardExpanded] = useState(false);

	const toggleCardExpansion = () => {
		setIsCardExpanded(!isCardExpanded);
	};

	const handleOrderBy = (newOrderBy) => {
		setOrder(newOrderBy);
	};

	// eslint-disable-next-line react-hooks/exhaustive-deps
	const handleSearchClick = (() => {
		dispatch(actions.getFeed({
			keywords: search.trim(),
			universityId,
			subjectId,
			minYear,
			maxYear,
			order,
			page: 0,
		}));
	})

	useEffect(() => {
		handleSearchClick();
		// eslint-disable-next-line react-hooks/exhaustive-deps
	}, [order]);


	const handleInputChange = (event) => {
		setSearch(event.target.value);
	};

	const handleInputKeyDown = (event) => {
		if (event.key === 'Enter') {
			if (document.activeElement === event.target) {
				handleSearchClick();
			}
		}
	};


	return (
		<div className="justify-content-center">
			<div className="Posts-container col-11 mx-auto">

				<div style={{ display: "flex", alignItems: "center" }}>

					<div className="container mt-4">
						<div className="col-md-12">
							<div className="card bg-darkgray rounded-3">
								<h5 className="card-header bg-black">
									<button className="btn btn-dark expand-button" style={{ width: '40px', marginLeft: '1rem' }} data-bs-toggle="collapse" data-bs-target="#card"
										onClick={() => { toggleCardExpansion(); }}>
										{isCardExpanded ? '-' : '+'}
									</button>
								</h5>

								<div id='card' className={`card-body`}>
									<UniversitySelector style={{ backgroundColor: '#212529', color: 'white', marginLeft: '0px !important' }} id="categoryId" className="custom-select my-1 mr-s m-2" data-testid="uni-selector"
										value={universityId} onChange={e => {setUniversityId(e.target.value); dispatch(actions.findSubjects(e.target.value)); setSubjectId('')}} />
									<SubjectSelector style={{ backgroundColor: '#212529', color: 'white', marginLeft: '0px !important' }} id="subjectId" className="custom-select my-1 mr-s m-2" data-testid="subject-selector"
													 value={subjectId} onChange={e => setSubjectId(e.target.value)} disabled={universityId===''}/>
									<div>
										<label className="col-md-12 col-form-label" style={{ paddingRight: '1rem' }}>
											Ordenar por:
										</label>

										<div className="btn-group">

											<button
												data-testid="pricea-button"
												className={`btn btn-dark ${order === 'PRICEA' ? 'active' : ''}`}
												onClick={() => { handleOrderBy('YEARA'); }}>
												Curso más antiguo
											</button>

											<button
												data-testid="priced-button"
												className={`btn btn-dark ${order === 'PRICED' ? 'active' : ''}`}
												onClick={() => { handleOrderBy('YEARD'); }}>
												Curso más reciente
											</button>

											<button
												data-testid="new-button"
												className={`btn btn-dark ${order === 'NEW' ? 'active' : ''}`}
												onClick={() => { handleOrderBy('NEW'); }}>
												Subidos recientemente
											</button>

											<button
												data-testid="old-button"
												className={`btn btn-dark ${order === 'OLD' ? 'active' : ''}`}
												onClick={() => { handleOrderBy('OLD'); }}>
												Subidos antiguamente
											</button>

											<button
												data-testid="popularity-button"
												className={`btn btn-dark ${order === 'POPULARITY' ? 'active' : ''}`}
												onClick={() => { handleOrderBy('POPULARITY'); }}>
												Popularidad
											</button>

											<button
												data-testid="sin-preferencia-button"
												className={`btn btn-dark ${order === '' ? 'active' : ''}`}
												onClick={() => { handleOrderBy(''); }}>
												Sin preferencia
											</button>

										</div>

										<div className="input-group col-12" style={{ marginTop: '0.6rem' }}>
											<label className="col-md-12 col-form-label" style={{ paddingRight: '1rem' }}>
												Filtrar por cursos:
											</label>
											<AcademicYearSelector style={{ backgroundColor: '#212529', color: 'white', marginLeft: '0px !important' }} id="categoryId" className="custom-select my-1 mr-s m-2" data-testid="category-selector"
															  value={minYear} onChange={e => setMinYear(e.target.value)} />

											<div className="px-1"> - </div>

											<AcademicYearSelector style={{ backgroundColor: '#212529', color: 'white', marginLeft: '0px !important' }} id="categoryId" className="custom-select my-1 mr-s m-2" data-testid="category-selector"
																  value={maxYear} onChange={e => setMaxYear(e.target.value)} />
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>


				</div>

				<div className="container mt-4">
					<div className="col-md-12">
						<div className="row">
							<div className="col-md-12">
								<div className="input-group outline rounded-3">
									<input
										data-testid="search-input"
										type="text"
										className="form-control"
										placeholder="Escribe tu búsqueda"
										onChange={handleInputChange}
										onKeyDown={handleInputKeyDown}
										style={{ marginBottom: '0px' }}
									/>
									<div className="input-group-append">
										<button
											data-testid="search-button"
											className="btn btn-dark"
											style={{ borderTopLeftRadius: '0', borderBottomLeftRadius: '0', boxShadow: '0 0 0 2px #28242c', background: 'dark-grey', height: '100%' }}
											type="button"
											onClick={handleSearchClick}
										>
											Buscar
										</button>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
				<Feed />
			</div>
		</div>
	);
};

export default FilterAndSearch;