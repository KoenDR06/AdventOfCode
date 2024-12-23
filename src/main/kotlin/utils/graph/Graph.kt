package me.koendev.utils.graph

import me.koendev.utils.println

abstract class BaseGraph

@Suppress("Unused", "MemberVisibilityCanBePrivate")
class Graph<T>: BaseGraph() {
    private val nodes: MutableMap<String, Node<T>> = mutableMapOf()
    private val edges: MutableList<Edge<T>> = mutableListOf()

    fun getNode(name: String) = if (nodes[name] == null) throw NodeDoesNotExistsException() else nodes[name]!!

    fun addNode(node: Node<T>, name: String) {
        if (name in nodes.keys) throw NodeExistsException()
        nodes[name] = node
    }

    fun removeNode(name: String) {
        if (nodes.remove(name) == null) throw NodeDoesNotExistsException()
    }

    fun addEdge(from: String, to: String) {
        if (from !in nodes.keys || to !in nodes.keys) throw NodeDoesNotExistsException()
        if (Edge(nodes[from]!!, nodes[to]!!) in edges) throw EdgeExistsException()
        if (Edge(nodes[to]!!, nodes[from]!!) in edges) throw EdgeExistsException()
        edges.add(Edge(nodes[from]!!, nodes[to]!!))
    }

    fun removeEdge(from: String, to: String) {
        if (!edges.remove(Edge(nodes[from]!!, nodes[to]!!))) throw NodeDoesNotExistsException()
    }

    fun getNeighbors(name: String): Map<String, Node<T>> {
        return getNeighbors(getNode(name))
    }

    fun getNeighbors(node: Node<T>): Map<String, Node<T>> {
        val neighbors = mutableListOf<Node<T>>()

        neighbors += edges.filter { edge ->
            edge.a == node
        }.map { edge ->
            edge.b
        }

        neighbors += edges.filter { edge ->
            edge.b == node
        }.map { edge ->
            edge.a
        }

        neighbors.println()

        return mapOf()
    }
}