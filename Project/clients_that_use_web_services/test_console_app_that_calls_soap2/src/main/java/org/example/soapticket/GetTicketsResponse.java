package org.example.soapticket;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

/**
 * <p>Java class for getTicketsResponse complex type.</p>
 *
 * <p>The following schema fragment specifies the expected content contained
 * within this class.</p>
 *
 * <pre>
 * &lt;complexType name="getTicketsResponse">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="return" type="{http://soap.ticket.hccis.ca/}trainTicket"
 *              maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "getTicketsResponse", propOrder = {
        "_return"
})
public class GetTicketsResponse {

    @XmlElement(name = "return")
    protected List<TrainTicket> _return;

    /**
     * Gets the value of the return list.
     *
     * <p>This returns a *live list*, not a snapshot. So if you call
     * <code>getReturn().add(newItem)</code>, the new item is added to the JAXB object.</p>
     *
     * @return list of TrainTicket objects
     */
    public List<TrainTicket> getReturn() {
        if (_return == null) {
            _return = new ArrayList<>();
        }
        return this._return;
    }
}
