//------------------------------------------------------------------------------
// <auto-generated>
//     Dieser Code wurde von einem Tool generiert.
//     Laufzeitversion:4.0.30319.42000
//
//     Änderungen an dieser Datei können falsches Verhalten verursachen und gehen verloren, wenn
//     der Code erneut generiert wird.
// </auto-generated>
//------------------------------------------------------------------------------

using NMF.Collections.Generic;
using NMF.Collections.ObjectModel;
using NMF.Expressions;
using NMF.Expressions.Linq;
using NMF.Models;
using NMF.Models.Collections;
using NMF.Models.Expressions;
using NMF.Models.Meta;
using NMF.Models.Repository;
using NMF.Serialization;
using NMF.Utilities;
using System;
using System.Collections;
using System.Collections.Generic;
using System.Collections.ObjectModel;
using System.Collections.Specialized;
using System.ComponentModel;
using System.Diagnostics;
using System.Linq;

namespace TTC2018.LiveContest.SocialNetwork
{
    
    
    /// <summary>
    /// The default implementation of the SocialNetworkRoot class
    /// </summary>
    [XmlNamespaceAttribute("https://www.transformation-tool-contest.eu/2018/social_media")]
    [XmlNamespacePrefixAttribute("social")]
    [ModelRepresentationClassAttribute("https://www.transformation-tool-contest.eu/2018/social_media#//SocialNetworkRoot")]
    public partial class SocialNetworkRoot : ModelElement, ISocialNetworkRoot, IModelElement
    {
        
        private static Lazy<ITypedElement> _postsReference = new Lazy<ITypedElement>(RetrievePostsReference);
        
        /// <summary>
        /// The backing field for the Posts property
        /// </summary>
        private ObservableCompositionOrderedSet<IPost> _posts;
        
        private static Lazy<ITypedElement> _usersReference = new Lazy<ITypedElement>(RetrieveUsersReference);
        
        /// <summary>
        /// The backing field for the Users property
        /// </summary>
        private ObservableCompositionOrderedSet<IUser> _users;
        
        private static IClass _classInstance;
        
        public SocialNetworkRoot()
        {
            this._posts = new ObservableCompositionOrderedSet<IPost>(this);
            this._posts.CollectionChanging += this.PostsCollectionChanging;
            this._posts.CollectionChanged += this.PostsCollectionChanged;
            this._users = new ObservableCompositionOrderedSet<IUser>(this);
            this._users.CollectionChanging += this.UsersCollectionChanging;
            this._users.CollectionChanged += this.UsersCollectionChanged;
        }
        
        /// <summary>
        /// The posts property
        /// </summary>
        [DesignerSerializationVisibilityAttribute(DesignerSerializationVisibility.Content)]
        [BrowsableAttribute(false)]
        [XmlElementNameAttribute("posts")]
        [XmlAttributeAttribute(false)]
        [ContainmentAttribute()]
        [ConstantAttribute()]
        public IOrderedSetExpression<IPost> Posts
        {
            get
            {
                return this._posts;
            }
        }
        
        /// <summary>
        /// The users property
        /// </summary>
        [DesignerSerializationVisibilityAttribute(DesignerSerializationVisibility.Content)]
        [BrowsableAttribute(false)]
        [XmlElementNameAttribute("users")]
        [XmlAttributeAttribute(false)]
        [ContainmentAttribute()]
        [ConstantAttribute()]
        public IOrderedSetExpression<IUser> Users
        {
            get
            {
                return this._users;
            }
        }
        
        /// <summary>
        /// Gets the child model elements of this model element
        /// </summary>
        public override IEnumerableExpression<IModelElement> Children
        {
            get
            {
                return base.Children.Concat(new SocialNetworkRootChildrenCollection(this));
            }
        }
        
        /// <summary>
        /// Gets the referenced model elements of this model element
        /// </summary>
        public override IEnumerableExpression<IModelElement> ReferencedElements
        {
            get
            {
                return base.ReferencedElements.Concat(new SocialNetworkRootReferencedElementsCollection(this));
            }
        }
        
        /// <summary>
        /// Gets the Class model for this type
        /// </summary>
        public new static IClass ClassInstance
        {
            get
            {
                if ((_classInstance == null))
                {
                    _classInstance = ((IClass)(MetaRepository.Instance.Resolve("https://www.transformation-tool-contest.eu/2018/social_media#//SocialNetworkRoot")));
                }
                return _classInstance;
            }
        }
        
        private static ITypedElement RetrievePostsReference()
        {
            return ((ITypedElement)(((ModelElement)(SocialNetworkRoot.ClassInstance)).Resolve("posts")));
        }
        
        /// <summary>
        /// Forwards CollectionChanging notifications for the Posts property to the parent model element
        /// </summary>
        /// <param name="sender">The collection that raised the change</param>
        /// <param name="e">The original event data</param>
        private void PostsCollectionChanging(object sender, NotifyCollectionChangedEventArgs e)
        {
            this.OnCollectionChanging("Posts", e, _postsReference);
        }
        
        /// <summary>
        /// Forwards CollectionChanged notifications for the Posts property to the parent model element
        /// </summary>
        /// <param name="sender">The collection that raised the change</param>
        /// <param name="e">The original event data</param>
        private void PostsCollectionChanged(object sender, NotifyCollectionChangedEventArgs e)
        {
            this.OnCollectionChanged("Posts", e, _postsReference);
        }
        
        private static ITypedElement RetrieveUsersReference()
        {
            return ((ITypedElement)(((ModelElement)(SocialNetworkRoot.ClassInstance)).Resolve("users")));
        }
        
        /// <summary>
        /// Forwards CollectionChanging notifications for the Users property to the parent model element
        /// </summary>
        /// <param name="sender">The collection that raised the change</param>
        /// <param name="e">The original event data</param>
        private void UsersCollectionChanging(object sender, NotifyCollectionChangedEventArgs e)
        {
            this.OnCollectionChanging("Users", e, _usersReference);
        }
        
        /// <summary>
        /// Forwards CollectionChanged notifications for the Users property to the parent model element
        /// </summary>
        /// <param name="sender">The collection that raised the change</param>
        /// <param name="e">The original event data</param>
        private void UsersCollectionChanged(object sender, NotifyCollectionChangedEventArgs e)
        {
            this.OnCollectionChanged("Users", e, _usersReference);
        }
        
        /// <summary>
        /// Gets the relative URI fragment for the given child model element
        /// </summary>
        /// <returns>A fragment of the relative URI</returns>
        /// <param name="element">The element that should be looked for</param>
        protected override string GetRelativePathForNonIdentifiedChild(IModelElement element)
        {
            int postsIndex = ModelHelper.IndexOfReference(this.Posts, element);
            if ((postsIndex != -1))
            {
                return ModelHelper.CreatePath("posts", postsIndex);
            }
            int usersIndex = ModelHelper.IndexOfReference(this.Users, element);
            if ((usersIndex != -1))
            {
                return ModelHelper.CreatePath("users", usersIndex);
            }
            return base.GetRelativePathForNonIdentifiedChild(element);
        }
        
        /// <summary>
        /// Resolves the given URI to a child model element
        /// </summary>
        /// <returns>The model element or null if it could not be found</returns>
        /// <param name="reference">The requested reference name</param>
        /// <param name="index">The index of this reference</param>
        protected override IModelElement GetModelElementForReference(string reference, int index)
        {
            if ((reference == "POSTS"))
            {
                if ((index < this.Posts.Count))
                {
                    return this.Posts[index];
                }
                else
                {
                    return null;
                }
            }
            if ((reference == "USERS"))
            {
                if ((index < this.Users.Count))
                {
                    return this.Users[index];
                }
                else
                {
                    return null;
                }
            }
            return base.GetModelElementForReference(reference, index);
        }
        
        /// <summary>
        /// Gets the Model element collection for the given feature
        /// </summary>
        /// <returns>A non-generic list of elements</returns>
        /// <param name="feature">The requested feature</param>
        protected override System.Collections.IList GetCollectionForFeature(string feature)
        {
            if ((feature == "POSTS"))
            {
                return this._posts;
            }
            if ((feature == "USERS"))
            {
                return this._users;
            }
            return base.GetCollectionForFeature(feature);
        }
        
        /// <summary>
        /// Gets the property name for the given container
        /// </summary>
        /// <returns>The name of the respective container reference</returns>
        /// <param name="container">The container object</param>
        protected override string GetCompositionName(object container)
        {
            if ((container == this._posts))
            {
                return "posts";
            }
            if ((container == this._users))
            {
                return "users";
            }
            return base.GetCompositionName(container);
        }
        
        /// <summary>
        /// Gets the Class for this model element
        /// </summary>
        public override IClass GetClass()
        {
            if ((_classInstance == null))
            {
                _classInstance = ((IClass)(MetaRepository.Instance.Resolve("https://www.transformation-tool-contest.eu/2018/social_media#//SocialNetworkRoot")));
            }
            return _classInstance;
        }
        
        /// <summary>
        /// The collection class to to represent the children of the SocialNetworkRoot class
        /// </summary>
        public class SocialNetworkRootChildrenCollection : ReferenceCollection, ICollectionExpression<IModelElement>, ICollection<IModelElement>
        {
            
            private SocialNetworkRoot _parent;
            
            /// <summary>
            /// Creates a new instance
            /// </summary>
            public SocialNetworkRootChildrenCollection(SocialNetworkRoot parent)
            {
                this._parent = parent;
            }
            
            /// <summary>
            /// Gets the amount of elements contained in this collection
            /// </summary>
            public override int Count
            {
                get
                {
                    int count = 0;
                    count = (count + this._parent.Posts.Count);
                    count = (count + this._parent.Users.Count);
                    return count;
                }
            }
            
            protected override void AttachCore()
            {
                this._parent.Posts.AsNotifiable().CollectionChanged += this.PropagateCollectionChanges;
                this._parent.Users.AsNotifiable().CollectionChanged += this.PropagateCollectionChanges;
            }
            
            protected override void DetachCore()
            {
                this._parent.Posts.AsNotifiable().CollectionChanged -= this.PropagateCollectionChanges;
                this._parent.Users.AsNotifiable().CollectionChanged -= this.PropagateCollectionChanges;
            }
            
            /// <summary>
            /// Adds the given element to the collection
            /// </summary>
            /// <param name="item">The item to add</param>
            public override void Add(IModelElement item)
            {
                IPost postsCasted = item.As<IPost>();
                if ((postsCasted != null))
                {
                    this._parent.Posts.Add(postsCasted);
                }
                IUser usersCasted = item.As<IUser>();
                if ((usersCasted != null))
                {
                    this._parent.Users.Add(usersCasted);
                }
            }
            
            /// <summary>
            /// Clears the collection and resets all references that implement it.
            /// </summary>
            public override void Clear()
            {
                this._parent.Posts.Clear();
                this._parent.Users.Clear();
            }
            
            /// <summary>
            /// Gets a value indicating whether the given element is contained in the collection
            /// </summary>
            /// <returns>True, if it is contained, otherwise False</returns>
            /// <param name="item">The item that should be looked out for</param>
            public override bool Contains(IModelElement item)
            {
                if (this._parent.Posts.Contains(item))
                {
                    return true;
                }
                if (this._parent.Users.Contains(item))
                {
                    return true;
                }
                return false;
            }
            
            /// <summary>
            /// Copies the contents of the collection to the given array starting from the given array index
            /// </summary>
            /// <param name="array">The array in which the elements should be copied</param>
            /// <param name="arrayIndex">The starting index</param>
            public override void CopyTo(IModelElement[] array, int arrayIndex)
            {
                IEnumerator<IModelElement> postsEnumerator = this._parent.Posts.GetEnumerator();
                try
                {
                    for (
                    ; postsEnumerator.MoveNext(); 
                    )
                    {
                        array[arrayIndex] = postsEnumerator.Current;
                        arrayIndex = (arrayIndex + 1);
                    }
                }
                finally
                {
                    postsEnumerator.Dispose();
                }
                IEnumerator<IModelElement> usersEnumerator = this._parent.Users.GetEnumerator();
                try
                {
                    for (
                    ; usersEnumerator.MoveNext(); 
                    )
                    {
                        array[arrayIndex] = usersEnumerator.Current;
                        arrayIndex = (arrayIndex + 1);
                    }
                }
                finally
                {
                    usersEnumerator.Dispose();
                }
            }
            
            /// <summary>
            /// Removes the given item from the collection
            /// </summary>
            /// <returns>True, if the item was removed, otherwise False</returns>
            /// <param name="item">The item that should be removed</param>
            public override bool Remove(IModelElement item)
            {
                IPost postItem = item.As<IPost>();
                if (((postItem != null) 
                            && this._parent.Posts.Remove(postItem)))
                {
                    return true;
                }
                IUser userItem = item.As<IUser>();
                if (((userItem != null) 
                            && this._parent.Users.Remove(userItem)))
                {
                    return true;
                }
                return false;
            }
            
            /// <summary>
            /// Gets an enumerator that enumerates the collection
            /// </summary>
            /// <returns>A generic enumerator</returns>
            public override IEnumerator<IModelElement> GetEnumerator()
            {
                return Enumerable.Empty<IModelElement>().Concat(this._parent.Posts).Concat(this._parent.Users).GetEnumerator();
            }
        }
        
        /// <summary>
        /// The collection class to to represent the children of the SocialNetworkRoot class
        /// </summary>
        public class SocialNetworkRootReferencedElementsCollection : ReferenceCollection, ICollectionExpression<IModelElement>, ICollection<IModelElement>
        {
            
            private SocialNetworkRoot _parent;
            
            /// <summary>
            /// Creates a new instance
            /// </summary>
            public SocialNetworkRootReferencedElementsCollection(SocialNetworkRoot parent)
            {
                this._parent = parent;
            }
            
            /// <summary>
            /// Gets the amount of elements contained in this collection
            /// </summary>
            public override int Count
            {
                get
                {
                    int count = 0;
                    count = (count + this._parent.Posts.Count);
                    count = (count + this._parent.Users.Count);
                    return count;
                }
            }
            
            protected override void AttachCore()
            {
                this._parent.Posts.AsNotifiable().CollectionChanged += this.PropagateCollectionChanges;
                this._parent.Users.AsNotifiable().CollectionChanged += this.PropagateCollectionChanges;
            }
            
            protected override void DetachCore()
            {
                this._parent.Posts.AsNotifiable().CollectionChanged -= this.PropagateCollectionChanges;
                this._parent.Users.AsNotifiable().CollectionChanged -= this.PropagateCollectionChanges;
            }
            
            /// <summary>
            /// Adds the given element to the collection
            /// </summary>
            /// <param name="item">The item to add</param>
            public override void Add(IModelElement item)
            {
                IPost postsCasted = item.As<IPost>();
                if ((postsCasted != null))
                {
                    this._parent.Posts.Add(postsCasted);
                }
                IUser usersCasted = item.As<IUser>();
                if ((usersCasted != null))
                {
                    this._parent.Users.Add(usersCasted);
                }
            }
            
            /// <summary>
            /// Clears the collection and resets all references that implement it.
            /// </summary>
            public override void Clear()
            {
                this._parent.Posts.Clear();
                this._parent.Users.Clear();
            }
            
            /// <summary>
            /// Gets a value indicating whether the given element is contained in the collection
            /// </summary>
            /// <returns>True, if it is contained, otherwise False</returns>
            /// <param name="item">The item that should be looked out for</param>
            public override bool Contains(IModelElement item)
            {
                if (this._parent.Posts.Contains(item))
                {
                    return true;
                }
                if (this._parent.Users.Contains(item))
                {
                    return true;
                }
                return false;
            }
            
            /// <summary>
            /// Copies the contents of the collection to the given array starting from the given array index
            /// </summary>
            /// <param name="array">The array in which the elements should be copied</param>
            /// <param name="arrayIndex">The starting index</param>
            public override void CopyTo(IModelElement[] array, int arrayIndex)
            {
                IEnumerator<IModelElement> postsEnumerator = this._parent.Posts.GetEnumerator();
                try
                {
                    for (
                    ; postsEnumerator.MoveNext(); 
                    )
                    {
                        array[arrayIndex] = postsEnumerator.Current;
                        arrayIndex = (arrayIndex + 1);
                    }
                }
                finally
                {
                    postsEnumerator.Dispose();
                }
                IEnumerator<IModelElement> usersEnumerator = this._parent.Users.GetEnumerator();
                try
                {
                    for (
                    ; usersEnumerator.MoveNext(); 
                    )
                    {
                        array[arrayIndex] = usersEnumerator.Current;
                        arrayIndex = (arrayIndex + 1);
                    }
                }
                finally
                {
                    usersEnumerator.Dispose();
                }
            }
            
            /// <summary>
            /// Removes the given item from the collection
            /// </summary>
            /// <returns>True, if the item was removed, otherwise False</returns>
            /// <param name="item">The item that should be removed</param>
            public override bool Remove(IModelElement item)
            {
                IPost postItem = item.As<IPost>();
                if (((postItem != null) 
                            && this._parent.Posts.Remove(postItem)))
                {
                    return true;
                }
                IUser userItem = item.As<IUser>();
                if (((userItem != null) 
                            && this._parent.Users.Remove(userItem)))
                {
                    return true;
                }
                return false;
            }
            
            /// <summary>
            /// Gets an enumerator that enumerates the collection
            /// </summary>
            /// <returns>A generic enumerator</returns>
            public override IEnumerator<IModelElement> GetEnumerator()
            {
                return Enumerable.Empty<IModelElement>().Concat(this._parent.Posts).Concat(this._parent.Users).GetEnumerator();
            }
        }
    }
}
