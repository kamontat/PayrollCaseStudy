package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.app.usecase.usecases.find;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.app.entity.Employee;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.app.usecase.HasResponse;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.app.usecase.TransactionalEmployeeGatewayUseCase;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.app.usecase.newversion.usecases.EmployeeGatewayFunctionUseCase;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.primary.admin.request.GetEmployeeRequest;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.primary.admin.response.GetEmployeeResponse;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.primary.admin.response.GetEmployeeResponse.EmployeeForGetEmployeeResponse;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.primary.admin.usecasefactories.UseCaseFactories;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.secondary.database.EmployeeGateway;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.secondary.database.TransactionalRunner;

public class GetEmployeeUseCase extends EmployeeGatewayFunctionUseCase<GetEmployeeRequest, GetEmployeeResponse> {

	private GetEmployeeResponseCreator getEmployeeResponseCreator = new GetEmployeeResponseCreator();
	
	public GetEmployeeUseCase(TransactionalRunner transactionalRunner, EmployeeGateway employeeGateway) {
		super(transactionalRunner, employeeGateway);
	}

	@Override
	protected GetEmployeeResponse executeInTransaction(GetEmployeeRequest request) {
		return getEmployeeResponseCreator.toResponse(employeeGateway.findById(request.employeeId));
	}

	
	private static class GetEmployeeResponseCreator {
		public GetEmployeeResponse toResponse(Employee employee) {
			return new GetEmployeeResponse(to(employee));
		}

		private EmployeeForGetEmployeeResponse to(Employee employee) {
			EmployeeForGetEmployeeResponse response = new EmployeeForGetEmployeeResponse();
			response.id = employee.getId();
			response.name = employee.getName();
			response.address = employee.getAddress();
			return response;
		}
		
	}
}

