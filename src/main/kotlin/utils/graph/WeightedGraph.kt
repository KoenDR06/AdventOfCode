package me.koendev.utils.graph

@Suppress("Unused", "MemberVisibilityCanBePrivate")
class WeightedGraph<T>: BaseGraph() {
    private val nodes: MutableMap<String, Node<T>> = mutableMapOf()
    private val edges: MutableMap<Edge<T>, Number> = mutableMapOf()

    fun getNode(name: String) = nodes[name]

    fun addNode(node: Node<T>, name: String) {
        if (name in nodes.keys) throw NodeExistsException()
        nodes[name] = node
    }

    fun removeNode(name: String) {
        if (nodes.remove(name) == null) throw NodeDoesNotExistsException()
    }

    fun addEdge(from: String, to: String, weight: Number) {
        if (from !in nodes.keys || to !in nodes.keys) throw NodeDoesNotExistsException()
        if (Edge(nodes[from]!!, nodes[to]!!) in edges) throw EdgeExistsException()
        edges[Edge(nodes[from]!!, nodes[to]!!)] = weight
    }

    fun removeEdge(from: String, to: String) {
        if (edges.remove(Edge(nodes[from]!!, nodes[to]!!)) == null) throw NodeDoesNotExistsException()
    }
}