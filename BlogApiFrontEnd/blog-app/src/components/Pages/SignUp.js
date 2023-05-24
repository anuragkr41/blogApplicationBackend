import {
	Button,
	Card,
	CardBody,
	CardHeader,
	Col,
	Container,
	Form,
	FormGroup,
	Input,
	Label,
	Row,
} from "reactstrap";
import Base from "../Base";

const SignUp = () => {
	return (
		<Base>
			<Container>
				<Row className="mt-4">
					<Col sm={{ size: 6, offset: 3 }}>
						<Card outline="dark" color="primary">
							<CardHeader>
								<h3>Fill Information to register!!</h3>
							</CardHeader>
							<CardBody>
								<Form>
									<FormGroup>
										<Label for="name">Enter Name</Label>
										<Input
											type="text"
											placeholder="Enter your name"
											id="name"
										/>
									</FormGroup>

									<FormGroup>
										<Label for="email">Enter Email</Label>
										<Input
											type="email"
											placeholder="Enter your email"
											id="email"
										></Input>
									</FormGroup>

									<FormGroup>
										<Label for="password">Enter Password</Label>
										<Input
											type="password"
											placeholder="Enter your password"
											id="password"
										></Input>
									</FormGroup>

									<FormGroup>
										<Label for="about">About yourself</Label>
										<Input
											type="textarea"
											placeholder="Tell us about yourself"
											id="about"
											style={{ height: "250px" }}
										></Input>
									</FormGroup>

									<Container className="text-center">
										<Button color="dark">Register</Button>
										<Button color="secondary" className="ms-2">
											Reset
										</Button>
									</Container>
								</Form>
							</CardBody>
						</Card>
					</Col>
				</Row>
			</Container>
		</Base>
	);
};

export default SignUp;
