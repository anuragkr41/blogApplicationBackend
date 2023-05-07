import Base from "../Base";

const About = () => {
	return (
		<Base>
			<h1>This is my about page</h1>
			<p> Welcome to about page</p>

			<div className="contatiner">
				<div className="row md-5">
					<div className="col md3">
						<div className="card">
							<div className="card-body"></div>
						</div>
					</div>
					<div className="col md3">
						<div className="card">
							<div className="card-body"></div>
						</div>
					</div>
					<div className="col md3">
						<div className="card">
							<div className="card-body"></div>
						</div>
					</div>
				</div>
			</div>
		</Base>
	);
};

export default About;
