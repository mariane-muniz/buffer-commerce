package com.buffer.commerce.facade;

import com.buffer.commerce.config.Action;
import com.buffer.commerce.config.DTO;
import com.buffer.commerce.config.Parameter;
import com.buffer.commerce.config.Status;
import com.buffer.commerce.model.Event;
import com.buffer.commerce.service.EventService;
import com.buffer.commerce.service.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class EventFacade {

    @Autowired
    private ApplicationContext applicationContext;
    @Autowired
    private GroupService groupService;
    @Autowired
    private EventService eventService;

    public boolean validateEvent(final String eventName) {
        final Event event = this.eventService.getEvent(eventName);
//        final List<Group> eventGroups = event.getGroups();
//        final boolean validGroup = this.groupService.validateGroup(eventGroups);
        return (event.isActive());
    }

    public DTO executeEvent(final String eventName, final Parameter parameter) {

        System.out.println("#====================================================");
        System.out.println("# START event execution > " + eventName);
        System.out.println("#====================================================");
        DTO result = null;
        final Event event = this.eventService.getEvent(eventName);

        if (this.validateEvent(eventName)) {
            System.out.println("Event is active");
            final Map<Integer, String> actions = event.getActions();
            result = this.processingList(actions, parameter);
        }
        else {
            result = new DTO().set("The event is not active", Status.FAIL);
        }
        System.out.println("#====================================================");
        System.out.println(" END event execution ");
        System.out.println("#====================================================");
        System.out.println();
        return result;
    }

    private DTO processingList(final Map<Integer, String> actions, final Parameter parameter) {
        DTO result = new DTO().set("Event without actions", Status.SUCCESS);
        for (String actionName : actions.values()) {
            final Action action = (Action) this.applicationContext.getBean(actionName);

            if (action.validateParameter(parameter).getStatus().equals(Status.SUCCESS)) {
                System.out.println("Action -> " + actionName + " -> valid parameter");
                final DTO execResult = action.execute(parameter);
                final Status status = execResult.getStatus();
                if (status.equals(Status.PARTIAL)) {
                    System.out.println("Action -> " + actionName + " -> Partial");
                    result = execResult;
                } else if (status.equals(Status.FAIL)) {
                    System.out.println("Action -> " + actionName + " -> Fail");
                    return execResult;
                }
                else {
                    System.out.println("Action -> " + actionName + " -> Success");
                }
            }
            else {
                System.out.println("Action -> " + actionName + " -> invalid parameter");
            }
        }
        return result;
    }
}
