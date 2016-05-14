package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.app.usecase;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.primary.admin.request.Request;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.secondary.database.EmployeeGateway;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.secondary.database.TransactionalRunner;

@Deprecated
public abstract class TransactionalEmployeeGatewayUseCase<R extends Request> extends TransactionalUseCase<R> {
	protected final EmployeeGateway employeeGateway;

	public TransactionalEmployeeGatewayUseCase(TransactionalRunner transactionalRunner, EmployeeGateway employeeGateway) {
		super(transactionalRunner);
		this.employeeGateway = employeeGateway;
	}

}