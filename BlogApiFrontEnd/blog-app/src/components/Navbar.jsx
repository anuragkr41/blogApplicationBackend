import { useState } from "react";
import { NavLink as ReactLink } from "react-router-dom";
import {
	Collapse,
	DropdownItem,
	DropdownMenu,
	DropdownToggle,
	Nav,
	NavItem,
	NavLink,
	Navbar,
	NavbarBrand,
	NavbarText,
	NavbarToggler,
	UncontrolledDropdown,
} from "reactstrap";

const CustomNavbar = () => {
	const [isOpen, setIsOpen] = useState(false);

	return (
		<div>
			<Navbar color="dark" dark expand="md" fixed="">
				<NavbarBrand href="/">reactStrap</NavbarBrand>
				<NavbarToggler onClick={() => setIsOpen(!isOpen)} />
				<Collapse isOpen={isOpen} navbar>
					<Nav className="me-auto" navbar>
						<NavItem>
							<NavLink tag={ReactLink} to="/">
								Home
							</NavLink>
						</NavItem>{" "}
						<NavItem>
							<NavLink tag={ReactLink} to="/about/">
								About
							</NavLink>
						</NavItem>{" "}
						<NavItem>
							<NavLink tag={ReactLink} to="/signup">
								SignUp
							</NavLink>
						</NavItem>{" "}
						<NavItem>
							<NavLink tag={ReactLink} to="/login/">
								Login
							</NavLink>
						</NavItem>
						<UncontrolledDropdown isNavbar nav>
							<DropdownToggle caret nav></DropdownToggle>
							<DropdownMenu right>
								<DropdownItem tag={ReactLink} to="/services">
									{" "}
									Services{" "}
								</DropdownItem>
								<DropdownItem> </DropdownItem>
								<DropdownItem>Contact Us</DropdownItem>
								<DropdownItem divider />
								<DropdownItem>Youtube</DropdownItem>
							</DropdownMenu>
						</UncontrolledDropdown>
					</Nav>
					<NavbarText> Youtube</NavbarText>
				</Collapse>
			</Navbar>
		</div>
	);
};

export default CustomNavbar;
