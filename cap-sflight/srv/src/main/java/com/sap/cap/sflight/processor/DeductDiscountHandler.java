package com.sap.cap.sflight.processor;

import static cds.gen.travelservice.TravelService_.TRAVEL;
import static java.lang.Boolean.TRUE;

import java.math.BigDecimal;
import java.math.MathContext;

import cds.gen.travelservice.TravelService;
import com.sap.cds.services.persistence.PersistenceService;
import org.springframework.stereotype.Component;

import com.sap.cds.ql.Update;
import com.sap.cds.ql.cqn.CqnUpdate;
import com.sap.cds.services.draft.DraftService;
import com.sap.cds.services.handler.EventHandler;
import com.sap.cds.services.handler.annotations.On;
import com.sap.cds.services.handler.annotations.ServiceName;

import cds.gen.travelservice.TravelDeductDiscountContext;
import cds.gen.travelservice.Travel;
import cds.gen.travelservice.TravelService_;
import cds.gen.travelservice.Travel_;

@Component
@ServiceName(TravelService_.CDS_NAME)
public class DeductDiscountHandler implements EventHandler {

	private final DraftService draftService;
	private final PersistenceService persistenceService;

	public DeductDiscountHandler(DraftService draftService, PersistenceService persistenceService) {
		this.draftService = draftService;
		this.persistenceService = persistenceService;
	}

	@On(entity = Travel_.CDS_NAME)
	public void deductDiscount(final TravelDeductDiscountContext context) {

		Travel travel = draftService.run(context.cqn()).single(Travel.class);

		BigDecimal discount = BigDecimal.valueOf(context.percent())
				.divide(BigDecimal.valueOf(10), new MathContext(3));

		BigDecimal deductedBookingFee = travel.bookingFee().subtract(travel.bookingFee().multiply(discount))
				.round(new MathContext(3));
		BigDecimal deductedTotalPrice = travel.totalPrice().subtract(travel.totalPrice().multiply(discount));

		travel.bookingFee(deductedBookingFee);
		travel.totalPrice(deductedTotalPrice);

		Travel update = Travel.create();
		update.totalPrice(deductedTotalPrice);
		update.bookingFee(deductedBookingFee);


		context.getCdsRuntime().requestContext().privilegedUser().run(ctx -> {
			//throw exception if travel.getIsActiveEntity is null!.
			if (TRUE.equals(travel.isActiveEntity())) {
				CqnUpdate updateCqn = Update.entity(TRAVEL)
						.where(t -> t.TravelUUID().eq(travel.travelUUID())).data(update);
				persistenceService.run(updateCqn);
			} else {
				CqnUpdate updateCqn = Update.entity(TRAVEL)
						.where(t -> t.TravelUUID().eq(travel.travelUUID()).and(t.IsActiveEntity().eq(travel.isActiveEntity()))).data(update);
				draftService.patchDraft(updateCqn);
			}
		});


		context.result(travel);
		context.setCompleted();

		System.out.println("Deducted discount for travel: " + travel.travelUUID() + " with discount: " + context.percent());
	}
}
