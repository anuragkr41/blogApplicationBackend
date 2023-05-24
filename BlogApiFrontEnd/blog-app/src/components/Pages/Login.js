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

const Login = () => {
	return (
		<Base>
			<Container>
				<Row className="mt-4">
					<Col
						sm={{
							size: 6,
							offset: 3,
						}}
					>
						<Card>
							<CardHeader>
								<h3>Login Here!!</h3>
							</CardHeader>
							<CardBody>
								<Form>
									<FormGroup>
										<Label for="Email">Enter Email</Label>
										<Input type="text" id="email " />
										<Label for="password">Enter Password</Label>
										<Input type="password" id="password" />
									</FormGroup>

									<Container className="text-center ">
										<Button color="dark">Login</Button>
										<Button className="ms-2" color="secondary">
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

export default Login;
