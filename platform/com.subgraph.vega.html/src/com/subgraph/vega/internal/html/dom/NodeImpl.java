package com.subgraph.vega.internal.html.dom;

import java.util.ArrayList;
import java.util.List;

import org.jsoup.nodes.DataNode;
import org.jsoup.nodes.Element;
import org.w3c.dom.DOMException;
import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.UserDataHandler;


public class NodeImpl implements Node {
	
	static NodeImpl createFromJsoupNode(org.jsoup.nodes.Node node, Document ownerDocument) {

		if(node == null)
			return null;
		else if(node instanceof org.jsoup.nodes.Element)
			return HTMLElementImpl.create((Element) node, ownerDocument);
		else if(node instanceof org.jsoup.nodes.TextNode)
			return new TextImpl((org.jsoup.nodes.TextNode) node, ownerDocument);
		else if(node instanceof org.jsoup.nodes.Comment)
			return new CommentImpl((org.jsoup.nodes.Comment) node, ownerDocument);
		else if(node instanceof org.jsoup.nodes.DataNode) 
			return new CharacterDataImpl((DataNode) node, ((DataNode)node).getWholeData(), ownerDocument);
		else
			return new NodeImpl(node, ownerDocument);		
	}
	
	
	static DOMException createReadOnlyException() {
		return new DOMException(DOMException.NO_MODIFICATION_ALLOWED_ERR, "Modifying the DOM is not permitted");
	}
	
	static DOMException createNoLevel3SupportException() {
		return new DOMException(DOMException.NOT_SUPPORTED_ERR, "This implementation does not support DOM Level 3");
	}
	
	static DOMException createNoXMLSupportException() {
		return new DOMException(DOMException.NOT_SUPPORTED_ERR, "No support for XML feature.");
	}	

	private final org.jsoup.nodes.Node jsoupNode; // Will be null for Attr nodes
	private Document ownerDocument;
		
	
	NodeImpl(org.jsoup.nodes.Node jsoupNode, Document ownerDocument) {
		this.jsoupNode = jsoupNode;
		this.ownerDocument = ownerDocument;
	}
	
	void setOwnerDocument(Document ownerDocument) {
		this.ownerDocument = ownerDocument;
	}
	
	@Override
	public String getNodeName() {
		return "#node";
	}

	@Override
	public String getNodeValue() {
		return null;
	}

	@Override
	public void setNodeValue(String nodeValue) throws DOMException {
		throw createReadOnlyException();		
	}

	@Override
	public short getNodeType() {
		return 0;
	}

	@Override
	public Node getParentNode() {
		if(jsoupNode == null)
			return null;
		
		return createFromJsoupNode(jsoupNode.parent(), ownerDocument);
	}

	@Override
	public NodeList getChildNodes() {
		if(jsoupNode == null || jsoupNode.childNodes().size() == 0)
			return NodeListImpl.emptyList;
		
		final List<NodeImpl> children = new ArrayList<NodeImpl>();
		for(org.jsoup.nodes.Node n: jsoupNode.childNodes()) 
			children.add(createFromJsoupNode(n, ownerDocument));
		return new NodeListImpl(children);
	}

	@Override
	public Node getFirstChild() {
		final NodeList nl = getChildNodes();
		if(nl.getLength() == 0)
			return null;
		else
			return nl.item(0);
	}

	@Override
	public Node getLastChild() {
		final NodeList nl = getChildNodes();
		if(nl.getLength() == 0)
			return null;
		else
			return nl.item(nl.getLength() - 1);
	}

	@Override
	public Node getPreviousSibling() {
		if(jsoupNode == null)
			return null;
		return createFromJsoupNode(jsoupNode.previousSibling(), ownerDocument);
	}

	@Override
	public Node getNextSibling() {
		if(jsoupNode == null)
			return null;
		return createFromJsoupNode(jsoupNode.nextSibling(), ownerDocument);
	}

	@Override
	public NamedNodeMap getAttributes() {
		return null;
	}

	@Override
	public Document getOwnerDocument() {
		return ownerDocument;
	}

	@Override
	public Node insertBefore(Node newChild, Node refChild) throws DOMException {
		throw createNoLevel3SupportException();
	}

	@Override
	public Node replaceChild(Node newChild, Node oldChild) throws DOMException {
		throw createNoLevel3SupportException();
	}

	@Override
	public Node removeChild(Node oldChild) throws DOMException {
		throw createNoLevel3SupportException();
	}

	@Override
	public Node appendChild(Node newChild) throws DOMException {
		throw createNoLevel3SupportException();
	}

	@Override
	public boolean hasChildNodes() {
		return getChildNodes().getLength() > 0;
	}

	@Override
	public Node cloneNode(boolean deep) {
		throw createReadOnlyException();
	}

	@Override
	public void normalize() {
		throw createNoLevel3SupportException();		
	}

	@Override
	public boolean isSupported(String feature, String version) {
		// ???
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public String getNamespaceURI() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getPrefix() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setPrefix(String prefix) throws DOMException {
		throw createReadOnlyException();		
	}

	@Override
	public String getLocalName() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean hasAttributes() {
		return false;
	}

	@Override
	public String getBaseURI() {
		throw createNoLevel3SupportException();
	}

	@Override
	public short compareDocumentPosition(Node other) throws DOMException {
		throw createNoLevel3SupportException();
	}

	@Override
	public String getTextContent() throws DOMException {
		throw createNoLevel3SupportException();
	}

	@Override
	public void setTextContent(String textContent) throws DOMException {
		throw createNoLevel3SupportException();		
	}

	@Override
	public boolean isSameNode(Node other) {
		throw createNoLevel3SupportException();
	}

	@Override
	public String lookupPrefix(String namespaceURI) {
		throw createNoLevel3SupportException();
	}

	@Override
	public boolean isDefaultNamespace(String namespaceURI) {
		throw createNoLevel3SupportException();
	}

	@Override
	public String lookupNamespaceURI(String prefix) {
		throw createNoLevel3SupportException();
	}

	@Override
	public boolean isEqualNode(Node arg) {
		throw createNoLevel3SupportException();
	}

	@Override
	public Object getFeature(String feature, String version) {
		throw createNoLevel3SupportException();
	}

	@Override
	public Object setUserData(String key, Object data, UserDataHandler handler) {
		throw createNoLevel3SupportException();
	}

	@Override
	public Object getUserData(String key) {
		throw createNoLevel3SupportException();
	}

}